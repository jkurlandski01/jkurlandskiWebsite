import unittest

from NER.jkurlandski.ner.nltk.NltkEntityExtractor import NltkEntityExtractor


class TestNltkEntityExtractor(unittest.TestCase):

    def testSentence(self):
        extractor = NltkEntityExtractor()
        extractor.readInput("John Smith wrote to Mary Jones.")

        entities = extractor.getNerEntities()
        expected = [('PERSON', 'John Smith'), ('PERSON', 'Mary Jones')]
        self.assertEqual(expected, entities)


if __name__ == '__main__':
    unittest.main()
