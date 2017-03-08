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


    def testOneSentence(self):
        inputStr = "John Smith wrote to Mary Jones."
        wrapper = NltkWrapper()

        parsedInput = wrapper.parseInput(inputStr)

        parsedInputList = TestNltkWrapper.generatorToList(parsedInput)

        self.assertEqual(1, len(parsedInputList))

        sentenceTree = parsedInputList[0]
        self.assertEqual('S', sentenceTree.label())

        # Verify number of children.
        self.assertEqual(6, len(sentenceTree))

        # Verify the first child--a Node.
        tokenNode = sentenceTree[0]
        self.assertEqual('PERSON', tokenNode.label())
        token, pos = tokenNode[0]
        self.assertEqual('John', token)
        self.assertEqual('NNP', pos)

        # Verify the third child--a Token.
        token, pos = sentenceTree[2]
        self.assertEqual('wrote', token)
        self.assertEqual('VBD', pos)


    # Test on two sentences.
    def testTwoSentences(self):
        inputStr = "John Smith wrote to Mary Jones. Jim Miller wept."
        wrapper = NltkWrapper()

        parsedInput = wrapper.parseInput(inputStr)

        parsedInputList = TestNltkWrapper.generatorToList(parsedInput)

        self.assertEqual(1, len(parsedInputList))
        print(str(wrapper.sentences))

        sentenceTree = parsedInputList[0]
        self.assertEqual('S', sentenceTree.label())

        # Verify number of children.
        self.assertEqual(9, len(sentenceTree))

        # Verify the first child--a Node.
        tokenNode = sentenceTree[0]
        self.assertEqual('PERSON', tokenNode.label())
        token, pos = tokenNode[0]
        self.assertEqual('John', token)
        self.assertEqual('NNP', pos)

        # Verify the third child--a Token.
        token, pos = sentenceTree[2]
        self.assertEqual('wrote', token)
        self.assertEqual('VBD', pos)

        # Verify the seventh child--a Node.
        tokenNode = sentenceTree[6]
        self.assertEqual('PERSON', tokenNode.label())
        token, pos = tokenNode[0]
        self.assertEqual('Jim', token)
        self.assertEqual('NNP', pos)
        token, pos = tokenNode[1]
        self.assertEqual('Miller', token)
        self.assertEqual('NNP', pos)



if __name__ == '__main__':
    unittest.main()
