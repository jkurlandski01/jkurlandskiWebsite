import nltk


class NltkWrapper(object):
    """Provides a simple interface to the NLTK resources required for named entity extraction.
    Initializes all the necessary resources only once, no matter how many documents are processed.
    Holds intermediate results as properties to allow separate analysis/debugging."""

    # TODO: Are there other options? Will any produce better results?
    # Initialize the components statically.
    language = 'english'
    chunker_pickle = 'chunkers/maxent_ne_chunker/english_ace_multiclass.pickle'
    sentTokenizer = nltk.load('tokenizers/punkt/{0}.pickle'.format(language))
    chunker = nltk.data.load(chunker_pickle)

    def __init__(self):
        # Initialize all properties.
        self.text = None
        self.sentences = None
        self.tokens = None
        self.posTags = None
        self.parsedInput = None


    def process(self, inText):
        self.text = inText
        self.sentenceTokenize()
        self.wordTokenize()
        self.doPosTagging()
        self.parseInput()

        return self.parsedInput


    def sentenceTokenize(self):
        """ Split the text into sentences. """
        self.sentences = NltkWrapper.sentTokenizer.tokenize(self.text)


    def wordTokenize(self):
        """ Split the text into tokens. """
        tokens = []

        for sentence in self.sentences:
            sentenceTokens = nltk.tokenize._treebank_word_tokenize(sentence)
            tokens.extend(sentenceTokens)

        # This list of a list looks buggy, but it seems to be correct.
        self.tokens = [tokens]


    def doPosTagging(self):
        """ Tag tokens for part of speech. """
        self.posTags = [nltk.pos_tag(token) for token in self.tokens]


    def parseInput(self):
        """ Perform NER. Not traditional parsing. """
        self.parsedInput = NltkWrapper.chunker.parse_sents(self.posTags)


    def getParse(self):
        strResult = ''
        treeResult = []
        for element in self.parsedInput:
            strResult += str(element) + '\n'
            treeResult.append(element)
        return treeResult, strResult


def printParse(inputStr):
    wrapper = NltkWrapper()
    wrapper.process(inputStr)
    trees, treeStr = wrapper.getParse()

    print('Input:')
    print('   ' + inputStr + '\n')

    print('Sentences:')
    for sentence in wrapper.sentences:
        print('   ' + sentence)
    print('')

    print('Parse as Tree:')
    print(trees)
    print()

    print('Parse as string:')
    print('   ' + treeStr)
    print()


if __name__ == '__main__':
    # View the NLTK parse of various inputs.
    printParse("Mary Jones")
    printParse("John Smith wrote to Mary Jones.")
    printParse("John Smith wrote to Mary Jones. Jim Miller wept.")
    printParse("The man who lives in the blue house dislikes the Martha Cummingham who lives in San Francisco.")
    printParse("I want to find a new hybrid automobile with Bluetooth.")

    pass

