import unittest

from NER.jkurlandski.ner import NerUtils


class NerUtilsTest(unittest.TestCase):

    def testEntityType(self):
        person = NerUtils.EntityType.Person
        org = NerUtils.EntityType.Organization
        loc = NerUtils.EntityType.Location

        # badEntity = NerUtils.EntityType.Org
        # self.assertRaises(AttributeError, NerUtils.EntityType.Org)
        with self.assertRaises(AttributeError) as context:
            NerUtils.EntityType.Org



if __name__ == '__main__':
    unittest.main()
