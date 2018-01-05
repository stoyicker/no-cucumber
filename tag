git commend -a
git tag -d $1
git tag -a $1 -m v$1 
git forcepush
rm -rf ~/.m2/repository/com/github/stoyicker/no-cucumber/$1
./gradlew install
