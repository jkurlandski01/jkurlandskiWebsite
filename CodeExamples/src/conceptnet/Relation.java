package conceptnet;

public enum Relation {Other("is somehow related to"), 
    Antonym("is the opposite of"), NotAntonym("is not the opposite of"), 
    AtLocation("is at"), NotAtLocation("is not at"), CapableOf("is capable of"), NotCapableOf("is not capable of"), 
    Causes("causes"), NotCauses("does not cause"), DefinedAs("is defined as"), NotDefinedAs("is not defined as"), 
    DerivedFrom("is derived from"), NotDerivedFrom("is not derived from"),
    HasA("has a"), NotHasA("doesn't have a"), HasContext("occurs in the context of"), NotHasContext("does not occur in the context of"), 
    HasPrerequisite("has a prerequisite of"), NotHasPrerequisite("does not have a prerequisite of"), 
    HasProperty("has the property of"), NotHasProperty("does not have the property of"), HasSubevent("has a subevent of"), NotHasSubevent("does not have a subevent of"), 
    IsA("is a"), NotIsA("is not a"), MemberOf("is a member of"), NotMemberOf("is not a member of"), PartOf("is part of"), NotPartOf("is not part of"), 
    RelatedTo("is related to"), NotRelatedTo("is not related to"), SimilarTo("is similar to"), NotSimilarTo("is not similar to"), 
    TranslationOf("is a translation of"), NotTranslationOf("is not a translation of"), 
    UsedFor("is used for"), NotUsedFor("is not used for");

    private String gloss;

    Relation(String str)   {
        gloss = str;
    }
    
    @Override
    public String toString()    {
        return gloss;
    }
}