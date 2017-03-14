import nltk

class NltkWrapper(object):
    """FIXME: Provides a simple interface to the NLTK resources required for named entity extraction.
    Initializes all the necessary resources only once, no matter how many documents are processed.
    Holds intermediate results as properties to allow separate analysis/debugging."""

    # FIXME: Are there other options? Will any produce better results?
    # Initialize the components statically.
    language = 'english'
    chunker_pickle = 'chunkers/maxent_ne_chunker/english_ace_multiclass.pickle'
    sentTokenizer = nltk.load('tokenizers/punkt/{0}.pickle'.format(language))
    # tagger = nltk.PerceptronTagger()
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

        # self.sentenceTokenize(text)
        for sentence in self.sentences:
            sentenceTokens = nltk.tokenize._treebank_word_tokenize(sentence)
            tokens.extend(sentenceTokens)

        # FIXME: This looks buggy to me.
        self.tokens = [tokens]


    def doPosTagging(self):
        """ Tag tokens for part of speech. """
        self.posTags = [nltk.pos_tag(token) for token in self.tokens]


    def parseInput(self):
        """ Parse each sentence. """
        self.parsedInput = NltkWrapper.chunker.parse_sents(self.posTags)


    def getParse(self):
        strResult = ''
        treeResult = []
        for element in self.parsedInput:
            strResult += str(element) + '\n'
            treeResult.append(element)
        return strResult, treeResult


def printParse(inputStr):
    wrapper = NltkWrapper()
    wrapper.process(inputStr)
    treeStr, trees = wrapper.getParse()

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
    printParse("The man who lives in the blue house dislikes the Martha Cumminham who lives in San Francisco.")

    pass

