CREATE TABLE nests
(
    nest_id         BIGINT AUTO_INCREMENT NOT NULL,
    nest_box_id     BIGINT                NOT NULL,
    date_of_survey  date                  NOT NULL,
    species         VARCHAR(45)           NULL,
    nestling_number INT                   NULL,
    observer        VARCHAR(100)          NOT NULL,
    CONSTRAINT pk_nests PRIMARY KEY (nest_id)
);

ALTER TABLE nests
    ADD CONSTRAINT FK_NESTS_ON_ID FOREIGN KEY (nest_box_id) REFERENCES nest_boxes (nest_box_id);