'''
Map SpaCy entity types to the standard Person/Organization/Location types.
Use static methods for default mapping. 
'''
from NER.jkurlandski.ner import NerUtils


class SpacyEntityTypeMapper(object):
    allEntityTypes = ['PERSON', 'NORP', 'FAC', 'ORG', 'GPE', 'LOC', 'PRODUCT', 'EVENT', 'WORK_OF_ART',
                      'LAW', 'LANGUAGE', 'DATE', 'TIME', 'PERCENT', 'MONEY', 'QUANTITY', 'ORDINAL', 'CARDINAL']

    targetedEntityTypes = ['ORG', 'PERSON', 'LOC', 'GPE']


    personTypes = ['PERSON']
    
    organizationTypes = ['ORG']
    
    locationTypes = ['LOC', 'GPE']


    @staticmethod
    def mapEntity(eType):
        if eType in SpacyEntityTypeMapper.personTypes:
            return NerUtils.EntityType.Person
        elif eType in SpacyEntityTypeMapper.organizationTypes:
            return NerUtils.EntityType.Organization
        elif eType in SpacyEntityTypeMapper.locationTypes:
            return NerUtils.EntityType.Location
        elif eType in SpacyEntityTypeMapper.allEntityTypes:
            return eType
        else:
            raise ValueError("Input param not mappable for static type definitions. Input param: " + eType)

        