lexer grammar BoardGameLexer;

BOARD_HEADER: 'Board:';

PHASE_HEADER: 'Phases:' ;

ROUNDS_HEADER: 'Rounds:' ;

TURNS_HEADER: 'Turns:';

MOVES_HEADER: 'Moves:';

PLAYER_HEADER : 'Players:';

THE: ('T' | 't') 'he';

GAME: ('G' | 'g') 'ame';

BOARD: ('B' | 'b') 'oard' 's'?;

PHASE: ('P' | 'p' )'hase' 's'?;

ROUND: ('R' | 'r') 'ound' 's'?;

TURN: ('T' | 't') 'urn' 's'?;

MOVE: ('M' | 'm') 'ove' 's'?;

HAS: 'has';

HAVE: 'have';

IS: 'is';

OR: 'or';

MAKES: 'makes';

PLAYER: ('P' | 'p')'layer' 's'?;

FIELD: ('F' | 'f')'ield' 's'?;

EVENT: ('E' | 'e') 'vent' 's'?;

PLAYED_BY: 'played by';

HAS_NEIGHBORS: 'has neighbor' 's'? ':';

ROUND_ROBIN: 'round-robin';

RANDOM_ORDER: 'random order';

BEFORE: ('B' | 'b') 'efore';

ACTION: ('A' | 'a') 'ction' 's'?;

AFTER: ('A' | 'a') 'fter';

//built in actions
PLACE_TOKEN_ON_EMPTY_FIELD: 'place token on empty field';
CHECK_WIN_CONDITIONS: 'check win conditions';

WIN_CONDITIONS_HEADER: 'Win conditions:';

WIN: ('W' | 'w') 'in:';

DRAW: ('D' | 'd') 'raw:';

//builtin win conditions
NO_EMPTY_FIELD: 'no empty field';
SAME_TOKEN_ON: 'same token on';

IN: 'In' | 'in';

A: 'A' | 'a';

PLAYED: 'played';

DOT: '.';

COLON: ':';

COMA: ',';

X: 'x';

ID: LETTER (LETTER | DIGIT)*;

fragment LETTER: [a-zA-Z];

fragment DIGIT: [0-9];

INT: DIGIT+;

COMMENT: '/*' .*? '*/' -> channel(HIDDEN);

WS: [ \t\r\n\u000C]+ -> skip;

ANY: .;
