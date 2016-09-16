Mars Rover Camunda Websocket Kata

Ein Spring Boot Projekt mit Embedded Camunda und Swagger, das ein BPMN Modell enthält welches den MarsRover Algorithmus abbildet.

Ein weiteres meiner Repos enthält eine kleine Angular2 Anwendung die als Client für dieses Projekt dient und den Mars Rover steuern kann.

Über localhost<port>/camunda kann das Camunda Cockpit erreicht werden.
Über localhost<port>/api kann swagger eerreicht werden.

Der Prozess kann auch über Swagger, bzw. eine REST Schnittstelle direkt angestoßen werden.


Der Mars Rover:
Ein kleiner "Gefährt", das einen String in der Form "ffrrbblff" entgegen nimmt.
Der String repräsentiert eine Reihe an Befehlen die daraufhin durch den Rover suageführt werden:

f: move forward
b: move backward
r: turn right
l: turn left

Das virtuelle Koordinaten System in dem sich der Rover bewegt ist 10 Felder hoch und breit.

Sobald der BPMN Prozess eine neue Position berechnet hat, wird der aktuelle Status des Marsrovers via Websocket versendet
und kann durch einen registrierten Client verarbeitet werden.

Der Client findet sich hier:
https://github.com/davidibl/MarsRoverCamundaClient

Das Projekt ist für ein ganz besonderes Dojo der LV1871 entstanden.

Viel Spass
David Ibl