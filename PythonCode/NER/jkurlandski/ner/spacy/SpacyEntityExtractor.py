from spacy.en import English

from NER.jkurlandski.ner import NerUtils
from NER.jkurlandski.ner.spacy.SpacyEntityTypeMapper import SpacyEntityTypeMapper


class SpacyEntityExtractor(object):
    """"""

    # One-time initialization of Spacy.
    nlp = English()

    def __init__(self):
        """"""
        self.reinitialize()


    def reinitialize(self):
        # Final output.
        self.entities = {}
        for entity in SpacyEntityTypeMapper.allEntityTypes:
            self.entities[entity] = []


    def readInput(self, text):
        self.reinitialize()

        self.text = text

        doc = SpacyEntityExtractor.nlp(text)

        self.extractEntityNames(doc)


    def extractEntityNames(self, doc):

        for entity in doc.ents:
            # Append to the entity list an entity-offset pair.
            self.entities[entity.label_].append((str(entity), entity.root.idx))


    def getEntitiesDict(self, filter=False):
        """
        Return a dictionary of entities where the keys are the entity types.

        :param filter: if True, return only the targeted entity types
        :return:
        """
        if not filter:
            return self.entities

        result = {}
        for eType in SpacyEntityTypeMapper.targetedEntityTypes:
            result[eType] = self.entities[eType]

        return result


    def getNerEntities(self, filter=False):
        """
        Return a list of NerEntity objects extracted.
        :param filter: if True, return only the targeted entity types
        :return:
        """
        entityDict = self.getEntitiesDict(filter)

        result = []

        for key in sorted(entityDict.keys()):
            mappedKey = SpacyEntityTypeMapper.mapEntity(key)
            for (content, offset) in entityDict[key]:
                entity = NerUtils.NerEntity(mappedKey, offset, content)
                result.append(entity)


        return result



if __name__ == '__main__':
    extractor = SpacyEntityExtractor()
    extractor.readInput("John Smith wrote to Mary Jones.")
    print(str(extractor.getNerEntities()))


