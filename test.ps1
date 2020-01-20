# this is a backup in case something goes terribly wrong 
#please don't run this, it's using my private key

git remote set-url origin https://isaacthoman:5577c145a0158599f3ee997a2fb2b3ddfb8a7a09@github.com/mars-robotics-association/test.git
$gitusername = git log -1 --pretty=format:'%an'
$gitemail = git log -1 --pretty=format:'%ae'

git config --global user.name "$gitusername" 
git config --global user.email "$gitemail" 

git checkout master
git reset --soft HEAD~1
git stash save "MasterChange"
git checkout ftc13474
git stash pop
git commit -a -m "Master Update"
git push
