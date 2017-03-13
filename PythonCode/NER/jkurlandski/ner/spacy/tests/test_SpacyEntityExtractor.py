import unittest

from NER.jkurlandski.ner.spacy.SpacyEntityExtractor import SpacyEntityExtractor


class TestSpacyEntityExtractor(unittest.TestCase):

    def testSimpleInput(self):
        inputStr = u"Harry Hanson worked with Susan Entwistle. They worked together in St. Louis, Missouri for the Monsanto Douglas Corporation."

        extractor = SpacyEntityExtractor()
        extractor.readInput(inputStr)

        entities = extractor.getNerEntities(filtered=True)

        self.assertEqual([], entities)


if __name__ == '__main__':
    unittest.main()
