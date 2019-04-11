grammar boardgame;

boardGame
: gameSpec boardSpec
;

gameSpec
: 'Game:' gameSpecLine*
;

boardSpec
: 'Board:' (boardSpecLine | fieldSpecLine)*
;

gameSpecLine
: GAME (gameIs | gameHas) DOT
;

gameIs
: IS playersDefinition
;

playersDefinition
: 'played by' INT PLAYER idList
;

gameHas
: HAS phasesDefinition
;

phasesDefinition
: INT PHASE idList
;

boardSpecLine
: BOARD HAS INT FIELD idList DOT
;

fieldSpecLine
: ID 'has neighbors:' idList DOT
;

idList
: varRef (', ' varRef)*
;

varRef
: ID
;

GAME: 'Game' | ('The ' | 'the ')? 'game';

BOARD: 'Board' | ('The ' | 'the ')? 'board';

HAS
: 'has'
;

IS
: 'is'
;

PLAYER
: ('player' | 'players')':'
;

PHASE
: ('phase' | 'phases') ':'
;

FIELD
: ('field' | 'fields') ':'
;

ID
: LETTER (LETTER | DIGIT)*
;


fragment LETTER
: [a-zA-Z]
;

INT
: DIGIT+
;

fragment DIGIT
: [0-9]
;

DOT
: '.'
;

WS
:  [ \t\r\n\u000C]+ -> skip
;

ANY
: .
;