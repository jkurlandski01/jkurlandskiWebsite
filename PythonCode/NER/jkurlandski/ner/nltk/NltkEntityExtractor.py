from NER.jkurlandski.ner.nltk.NltkWrapper import NltkWrapper


class NltkEntityExtractor(object):
    """
    Extract named entities from the output of an NltkWrapper.
    """

    IgnoredNodeLabels = ['S']

    def __init__(self):
        """"""
        self.initProperties()


    def initProperties(self):
        """ Reinitialize this object's properties for a new sentence."""
        self.parseTrees = []
        self.entities = {}


    def readInput(self, inputStr):
        self.initProperties()

        self.text = inputStr

        wrapper = NltkWrapper()
        parsedInput = wrapper.process(inputStr)

        for tree in parsedInput:
            self.parseTrees.append(tree)
            self.extractEntityNames(tree)

        return self.entities


    def extractEntityNames(self, tree):
        """
        Process the NltkWrapper tree, loading self.entities with the entities found.
        Combines two successive entities of the same type into one.
        """

        # FIXME: This method is more complex than it needs to be--it doesn't have to be recursive since all
        # nodes are just one level down.
        if hasattr(tree, 'label') and tree.label:
            if tree.label() not in NltkEntityExtractor.IgnoredNodeLabels:
                if not self.entities.get(tree.label()):
                    self.entities[tree.label()] = []
                self.entities[tree.label()].append(' '.join([child[0] for child in tree]))
            else:
                lastChild = None
                for child in tree:
                    if lastChild and self.isSameEntityType(lastChild, child):

                        # print "Same: " + str(lastChild) + "; " + str(child)

                        # Append the new entity to the list.
                        self.extractEntityNames(child)

                        # Now merge last two elements of entities list.
                        entityType = child.label()
                        entityList = self.entities[entityType]

                        # Remove the last two.
                        last = entityList[-1]
                        penultimate = entityList[-2]
                        entityList = entityList[:-2]

                        mergedString = ' '.join([penultimate, last])
                        entityList.append(mergedString)

                        # Update the object
                        self.entities[entityType] = entityList
                    else:
                        self.extractEntityNames(child)

                    lastChild = child


    def isSameEntityType(self, child1, child2):
        result = False

        if hasattr(child1, 'label') and child1.label:
            if hasattr(child2, 'label') and child2.label:
                if child1.label() == child2.label():
                    result = True

        return result


    def getNerEntities(self):
        """
        :return: a list of entity type-entity pairs
        """
        result = []

        for key in sorted(self.entities.keys()):
            for content in self.entities[key]:
                result.append((key, content))

        return result


if __name__ == '__main__':
    extractor = NltkEntityExtractor()
    extractor.readInput("John Smith wrote to Mary Jones.")
    print(extractor.parseTrees)
    print(str(extractor.getNerEntities()))
    pass