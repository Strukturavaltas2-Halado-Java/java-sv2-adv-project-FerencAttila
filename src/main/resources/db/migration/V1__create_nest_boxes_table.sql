CREATE TABLE nest_boxes
(
    nest_box_id     BIGINT AUTO_INCREMENT NOT NULL,
    nest_box_number VARCHAR(10)           NOT NULL,
    direction       VARCHAR(2)            NULL,
    height          DOUBLE                NULL,
    eov_x           INT                   NULL,
    eov_y           INT                   NULL,
    CONSTRAINT pk_nest_boxes PRIMARY KEY (nest_box_id)
);

ALTER TABLE nest_boxes
    ADD CONSTRAINT uc_nest_boxes_nest_box_number UNIQUE (nest_box_number);