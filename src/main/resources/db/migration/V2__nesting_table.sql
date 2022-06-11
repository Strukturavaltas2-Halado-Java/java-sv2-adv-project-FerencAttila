create table if not exists
    nesting
(
    id                    bigint auto_increment primary key,
    nest_box_id           varchar(10)  not null,
    survey_date           date         not null,
    species               varchar(45),
    eggs_number           tinyint,
    nestlings_number      tinyint,
    nestlings_age         varchar(255),
    scrambled_eggs_number tinyint,
    dead_nestlings_number tinyint,
    dead_adults_number    tinyint,
    mortality_desc        text,
    notes                 text,
    observer              varchar(100) not null,
    constraint `fk_nesting_nest_box`
        foreign key (nest_box_id) references nest_boxes (nest_box_id)
)