CREATE TABLE certificate
(
    id   int primary key not null,
    name nvarchar(10)    not null
);

CREATE TABLE commodity_code
(
    id          nvarchar(50) primary key not null,
    code        nchar(10)                not null,
    suffix      nchar(2)                 not null,
    description nvarchar(max)            not null,
    parent_id   nvarchar(50),
    source_name nvarchar(50)             not null,
    source_id   nvarchar(50)             not null,
    foreign key (parent_id) references commodity_code (id)
);

CREATE TABLE species
(
    id          int primary key identity,
    eppo        nchar(6),
    simple_name nvarchar(100),
    rank        nvarchar(100),
    class       nvarchar(100),
    family      nvarchar(100),
    genus       nvarchar(100),
    species     nvarchar(100),
    source_name nvarchar(50) not null,
    source_id   nvarchar(50) not null
);

CREATE TABLE commodity
(
    id             int primary key identity,
    certificate    int,
    commodity_code nvarchar(50),
    species        int
);

-- Tables for GBIF import
CREATE TABLE taxon
(
    taxon_id                   int,
    dataset_id                 nvarchar(36),
    parent_name_usage_id       nvarchar(max),
    accepted_name_usage_id     int,
    original_name_usage_id     int,
    scientific_name            nvarchar(max),
    scientific_name_authorship nvarchar(max),
    canonical_name             nvarchar(max),
    generic_name               nvarchar(max),
    specific_epithet           nvarchar(max),
    infraspecific_epithet      nvarchar(max),
    taxon_rank                 nvarchar(max),
    name_according_to          nvarchar(max),
    name_published_in          nvarchar(max),
    taxonomic_status           nvarchar(max),
    nomenclatural_status       nvarchar(max),
    taxon_remarks              nvarchar(max),
    kingdom                    nvarchar(max),
    phylum                     nvarchar(max),
    class                      nvarchar(max),
    [order]                    nvarchar(max),
    family                     nvarchar(max),
    genus                      nvarchar(max)
);

CREATE TABLE vernacular_name
(
    taxon_id        int,
    vernacular_name nvarchar(max),
    language        nvarchar(max),
    country         nvarchar(max),
    country_code    nvarchar(max),
    sex             nvarchar(max),
    life_stage      nvarchar(max),
    source          nvarchar(max)
);
