git commend -a
git tag -d $1
git tag -a $1 -m v$1 
git forcepush
rm -rf ~/.m2/repository/com/github/stoyicker/no-cucumber-annotations/$1
rm -rf ~/.m2/repository/com/github/stoyicker/no-cucumber-processors/$1
./gradlew build publishToMavenLocal
