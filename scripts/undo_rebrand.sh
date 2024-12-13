#!/bin/sh

find ./src/main/assets/help/ -type f -name '*.html' | xargs sed -i 's/github.com\/ArcaneChat/get.delta.chat/g'
find ./src/main/assets/help/ -type f -name '*.html' | xargs sed -i 's/ArcaneChat/Delta Chat/g'
find ./src/ -type f -name 'strings.xml' | xargs sed -i 's/github.com\/ArcaneChat/get.delta.chat/g'
find ./src/ -type f -name 'strings.xml' | xargs sed -i 's/ArcaneChat/Delta Chat/g'
sed -i 's/>Delta Chat</>ArcaneChat</g' ./src/main/res/values/strings.xml
