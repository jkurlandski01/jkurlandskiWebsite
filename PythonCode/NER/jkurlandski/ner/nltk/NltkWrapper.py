import nltk

class NltkWrapper(object):
    """FIXME: Provides a simple interface to the NLTK resources required for named entity extraction.
    Initializes all the necessary resources only once, no matter how many documents are processed.
    Holds intermediate results as properties to allow separate analysis/debugging."""

    language = 'english'

    # FIXME: Are there other options? Will any produce better results?
    chunker_pickle = 'chunkers/maxent_ne_chunker/english_ace_multiclass.pickle'

    def __init__(self):
        # Initialize all properties.
        self.text = None
        self.sentences = None
        self.tokens = None
        self.posTags = None
        self.chunkedSentences = None

        # Initialize all resources.
        # FIXME: Why aren't these static? Why is the chunker_pickle static?
        self.sentTokenizer = nltk.load('tokenizers/punkt/{0}.pickle'.format(NltkWrapper.language))
        self.tagger = nltk.PerceptronTagger()
        self.chunker = nltk.data.load(NltkWrapper.chunker_pickle)

        pass


    def sentenceTokenize(self, text):
        """ Split the text into sentences. """
        self.text = text

        self.sentences = self.sentTokenizer.tokenize(text)
        return self.sentences


    def wordTokenize(self, text):
        """ Split the text into tokens. """
        tokens = []

        self.sentenceTokenize(text)
        for sentence in self.sentences:
            sentenceTokens = nltk.tokenize._treebank_word_tokenize(sentence)
            tokens.extend(sentenceTokens)

        # This looks buggy to me.
        self.tokens = [tokens]

        return self.tokens


    def doPosTagging(self, text):
        """ Tag tokens for part of speech. """
        self.wordTokenize(text)

        self.posTags = [nltk.pos_tag(token) for token in self.tokens]

        return self.posTags


    def chunkSentences(self, text):
        """ Parse/process each sentence. """
        self.doPosTagging(text)

        self.chunkedSentences = self.chunker.parse_sents(self.posTags)

        return self.chunkedSentences


if __name__ == '__main__':
    pass

