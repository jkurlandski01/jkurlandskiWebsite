from spacy.en import English

from NER.jkurlandski.ner import NerUtils
from NER.jkurlandski.ner.spacy.SpacyEntityTypeMapper import SpacyEntityTypeMapper


class SpacyEntityExtractor(object):
    """"""

    nlp = English()

    def __init__(self):
        """"""
        self.init()


    def init(self):
        self.entities = {}
        for entity in SpacyEntityTypeMapper.allEntityTypes:
            self.entities[entity] = []


    def readInput(self, text):
        self.init()

        self.text = text

        doc = SpacyEntityExtractor.nlp(text)

        self.extractEntityNames(doc)

    def extractEntityNames(self, doc):

        for entity in doc.ents:
            # Append to the entity list an entity-offset pair.
            self.entities[entity.label_].append((str(entity), entity.root.idx))

    '''
     Return a dictionary of entities where the keys are the entity types.
     If filtered is True, return only the targeted entity types.
    '''
    def getEntitiesDict(self, filter=False):
        if not filter:
            return self.entities

        result = {}
        for eType in SpacyEntityTypeMapper.targetedEntityTypes:
            result[eType] = self.entities[eType]

        return result

    '''
     Return a list of NerEntity objects extracted.
     If filtered is True, return only the targeted entity types.
    '''
    def getNerEntities(self, filtered):
        entityDict = self.getEntitiesDict(filtered)

        result = []

        for key in sorted(entityDict.keys()):
            mappedKey = SpacyEntityTypeMapper.mapEntity(key)
            for (content, offset) in entityDict[key]:
                entity = NerUtils.NerEntity(mappedKey, offset, content)
                result.append(entity)


        return result


    def getPersons(self):
        return self.entities['PERSON']

    def getOrganizations(self):
        return self.entities['ORG']

    def getLocations(self):
        return self.entities['LOC']

    def getGPEs(self):
        return self.entities['GPE']



if __name__ == '__main__':
    pass

