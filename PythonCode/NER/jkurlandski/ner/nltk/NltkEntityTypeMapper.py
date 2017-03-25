"""
Map NLTK entity types to the standard Person/Organization/Location types.
Use static methods for default mapping. To extend with additional mappings,
allow instantiation of an object and provide a means of creating a separate
mapEntity() method for the object.
"""
# TODO: Use this in NltkEntityExtractor.

from NER.jkurlandski.ner import NerUtils


class NltkEntityTypeMapper(object):
    allEntityTypes = ['PERSON', 'ORGANIZATION', 'LOCATION', 'DATE', 'TIME', 'PERCENT', 'MONEY',
                      'FACILITY', 'GPE']

    targetedEntityTypes = ['ORGANIZATION', 'PERSON', 'LOCATION', 'GPE']

    personTypes = ['PERSON']
    
    organizationTypes = ['ORGANIZATION']
    
    locationTypes = ['LOCATION', 'GPE']


    @staticmethod
    def mapEntity(eType):
        if eType in NltkEntityTypeMapper.personTypes:
            return NerUtils.EntityType.Person
        elif eType in NltkEntityTypeMapper.organizationTypes:
            return NerUtils.EntityType.Organization
        elif eType in NltkEntityTypeMapper.locationTypes:
            return NerUtils.EntityType.Location
        elif eType in NltkEntityTypeMapper.allEntityTypes:
            return eType
        else:
            raise ValueError("Input param not mappable. Input param: " + eType)


        