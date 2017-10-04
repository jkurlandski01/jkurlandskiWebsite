from collections import namedtuple
from enum import Enum

NerEntity = namedtuple("NerEntity", "eType, offset, content")

# Person, Organization, Location
EntityType = Enum('EntityType', ['Person', 'Organization', 'Location'])

