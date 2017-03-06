import unittest

from NER.jkurlandski.ner.nltk.NltkWrapper import NltkWrapper


class TestNltkWrapper(unittest.TestCase):

    @staticmethod
    def generatorToList(generator):
        result = []
        for element in generator:
            result.append(element)
            print(str(element))
        return result


    def testBasic(self):
        inputStr = "John Smith wrote to Mary Jones."
        wrapper = NltkWrapper()

        chunkedSentences = wrapper.chunkSentences(inputStr)

        sentences = TestNltkWrapper.generatorToList(chunkedSentences)

        self.assertEqual(1, len(sentences))

        sentenceTree = sentences[0]
        self.assertEqual('S', sentenceTree.label())

        # Verify number of sentences.
        self.assertEqual(6, len(sentenceTree))

        # Verify just the first token node.
        tokenNode = sentenceTree[0]
        self.assertEqual('PERSON', tokenNode.label())
        token, pos = tokenNode[0]
        self.assertEqual('John', token)
        self.assertEqual('NNP', pos)


if __name__ == '__main__':
    unittest.main()