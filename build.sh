#!/bin/sh

set -eu

cd webapps/EnseirbWebXMLWebapp
mkdir -p WEB-INF/classes
javac -sourcepath src -encoding cp1252 -d WEB-INF/classes -cp 'WEB-INF/lib/*:../../lib/*' src/fr/enseirb/webxml/servlet/*.java
