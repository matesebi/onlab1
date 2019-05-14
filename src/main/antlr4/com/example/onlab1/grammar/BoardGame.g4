parser grammar BoardGame;

options { tokenVocab=BoardGameLexer; }

boardGame
: gameName COMMENT? board phases rounds turns moves winConditions players
;

gameName
: identifier COLON
;

board
: BOARD_HEADER boardFields field*
;

phases
: PHASE_HEADER phasesDefinition phase+
;

rounds
: ROUNDS_HEADER round+
;

turns
: TURNS_HEADER turn+
;

moves
: MOVES_HEADER move+
;

winConditions
: WIN_CONDITIONS_HEADER (win | draw)+
;

players
: PLAYER_HEADER playersDefinition player*
;

boardFields
: THE? BOARD HAS number FIELD COLON idList DOT
;

field
: identifier HAS_NEIGHBORS idList DOT
;

phasesDefinition
: THE? GAME HAS number PHASE COLON idList DOT
;

phase
: identifier HAS number ROUND COLON idList DOT
;

round
: IN identifier PLAYER HAVE A turnList IN roundOrder DOT
;

turn
: IN identifier A PLAYER MAKES turnMoves
;

move
: identifier COLON moveBefore? moveAction moveAfter?
;

win
: WIN builtinWinCondition DOT
;

draw
: DRAW builtinWinCondition DOT
;

playersDefinition
: THE? GAME IS PLAYED_BY number PLAYER COLON idList DOT
;

player
: identifier HAS playerTokens DOT
;

playerTokens
: playerToken ((COMA playerToken)* OR playerToken)?
;

playerToken
: number X identifier
;

turnList
: identifier ((COMA identifier)* OR identifier)?
;

roundOrder
: ROUND_ROBIN | RANDOM_ORDER
;

turnMoves
: turnMove ((COMA turnMove)* OR turnMove)? MOVE DOT
;

turnMove
: number identifier
;

moveBefore
: BEFORE COLON builtinAction DOT
;

moveAction
: ACTION COLON builtinAction DOT
;

moveAfter
: AFTER COLON builtinAction DOT
;

builtinWinCondition
: NO_EMPTY_FIELD | sameTokenOn
;

builtinAction
: PLACE_TOKEN_ON_EMPTY_FIELD | CHECK_WIN_CONDITIONS
;

sameTokenOn
: SAME_TOKEN_ON COLON idList
;

idList
: identifier (COMA identifier)*
;

identifier
: ID
;

number
: INT
;
