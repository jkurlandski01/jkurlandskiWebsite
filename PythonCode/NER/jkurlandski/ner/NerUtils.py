from collections import namedtuple

NerEntity = namedtuple("NerEntity", "eType, offset, content")

# Person, Organization, Location
class Enum(set):
    def __getattr__(self, name):
        if name in self:
            return name
        raise AttributeError("No such enum value " + name)
EntityType = Enum(["Person", "Organization", "Location"])

