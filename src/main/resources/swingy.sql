CREATE TABLE COORDINATES(
    ID UUID,
    X INTEGER,
    Y INTEGER,
    CONSTRAINT ID_COORDINATES_PK PRIMARY KEY (ID)
);

CREATE TABLE WEAPON(
    ID UUID,
    ATTACK INTEGER,
    NAME VARCHAR,
    CONSTRAINT ID_WEAPON_PK PRIMARY KEY (ID)
);

CREATE TABLE HELM(
    ID UUID,
    HIT_POINTS INTEGER,
    NAME VARCHAR,
    CONSTRAINT ID_HELM_PK PRIMARY KEY (ID)
);

CREATE TABLE CHARACTER_CLASS(
    ID UUID,
    CLASS_NAME VARCHAR,
    SPECIAL_TALENT VARCHAR,
    CONSTRAINT ID_CHARACTER_CLASS_PK PRIMARY KEY (ID)
);

CREATE TABLE ARMOR(
    ID UUID,
    DEFENSE INTEGER,
    NAME VARCHAR,
    CONSTRAINT ID_ARMOR_PK PRIMARY KEY (ID)
);

CREATE TABLE HERO(
    ID UUID,
    COORDINATES UUID,
    CHARACTER_CLASS UUID,
    WEAPON UUID,
    ARMOR UUID,
    HELM UUID,
    LEVEL INTEGER,
    EXPERIENCE INTEGER,
    ATTACK INTEGER,
    DEFENSE INTEGER,
    HIT_POINTS INTEGER,
    MAX_HP INTEGER,
    HP INTEGER,
    MANA INTEGER,
    NAME VARCHAR,
    CONSTRAINT COORDINATES_FK FOREIGN KEY (COORDINATES) REFERENCES COORDINATES(ID),
    CONSTRAINT CHARACTER_CLASS_FK FOREIGN KEY (CHARACTER_CLASS) REFERENCES CHARACTER_CLASS(ID),
    CONSTRAINT WEAPON FOREIGN KEY (WEAPON) REFERENCES WEAPON(ID),
    CONSTRAINT ARMOR FOREIGN KEY (ARMOR) REFERENCES ARMOR(ID),
    CONSTRAINT HELM FOREIGN KEY (HELM) REFERENCES HELM(ID),
    CONSTRAINT ID_NAME_HERO_PK PRIMARY KEY (ID, NAME)
);