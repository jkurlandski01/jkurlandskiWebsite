import unittest

from NER.jkurlandski.ner.nltk.NltkEntityExtractor import NltkEntityExtractor


class TestNltkEntityExtractor(unittest.TestCase):

    def testOneSentence(self):
        inputStr = "John Smith wrote to Mary Jones."
        extractor = NltkEntityExtractor()
        extractor.readInput(inputStr)

        entities = extractor.getNerEntities()
        expected = [('PERSON', 'John Smith'), ('PERSON', 'Mary Jones')]
        self.assertEqual(expected, entities)


    def testTwoSentences(self):
        inputStr = u"Harry Hanson worked with Susan Entwistle. They worked together in St. Louis, Missouri for the Monsanto Douglas Corporation."
        extractor = NltkEntityExtractor()
        extractor.readInput(inputStr)

        entities = extractor.getNerEntities()
        expected = [('GPE', 'St. Louis'),
                    ('ORGANIZATION', 'Monsanto Douglas Corporation'),
                    ('PERSON', 'Harry Hanson'),
                    ('PERSON', 'Susan Entwistle'),
                    ('PERSON', 'Missouri')]

        self.assertEqual(expected, entities)


if __name__ == '__main__':
    unittest.main()
