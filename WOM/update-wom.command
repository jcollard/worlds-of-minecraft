#!/bin/bash -e

mkdir -p /Library/worlds_of_minecraft_cache/1.16.0/backups
cd /Library/worlds_of_minecraft_cache/1.16.0/backups
BACKUP_FILENAME="/Library/worlds_of_minecraft_cache/1.16.0/backups/$(date).zip"
echo "Before we update, let's make a backup. Press Enter to continue"
read -p ""
zip -r "$BACKUP_FILENAME" /Library/worlds_of_minecraft_cache/1.16.0/eclipse-project/projects
clear
echo "Backup complete!"
echo "Updating..."
cd /Library/worlds_of_minecraft_cache/1.16.0/eclipse-project/projects/
git remote set-url origin https://github.com/jcollard/worlds-of-minecraft.git
git reset --hard
git pull -Xtheirs --no-edit
echo "\n\nUpdate Complete! You may now close this window."

