import unittest

from NER.jkurlandski.ner.NerUtils import NerEntity
from NER.jkurlandski.ner.spacy.SpacyEntityExtractor import SpacyEntityExtractor


class TestSpacyEntityExtractor(unittest.TestCase):

    def testOneSentence(self):
        inputStr = "John Smith wrote to Mary Jones."
        extractor = SpacyEntityExtractor()
        extractor.readInput(inputStr)

        entities = extractor.getNerEntities(filter=True)
        expected = [NerEntity(eType='Person', offset=5, content='John Smith'),
                    NerEntity(eType='Person', offset=25, content='Mary Jones')]

        self.assertEqual(expected, entities)

    def testTwoSentences(self):
        inputStr = u"Harry Hanson worked with Susan Entwistle. They worked together in St. Louis, Missouri for the Monsanto Douglas Corporation."
        extractor = SpacyEntityExtractor()
        extractor.readInput(inputStr)

        entities = extractor.getNerEntities(filter=True)
        expected = [NerEntity(eType='Location', offset=70, content='St. Louis'),
                     NerEntity(eType='Location', offset=77, content='Missouri'),
                     NerEntity(eType='Organization', offset=111, content='the Monsanto Douglas Corporation'),
                     NerEntity(eType='Person', offset=6, content='Harry Hanson'),
                     NerEntity(eType='Person', offset=31, content='Susan Entwistle')]
        self.assertEqual(expected, entities)


if __name__ == '__main__':
    unittest.main()
