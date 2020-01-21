#Hacking is for weebs, don't steal this

git remote set-url origin https://isaacthoman:5577c145a0158599f3ee997a2fb2b3ddfb8a7a09@github.com/mars-robotics-association/test.git
$gitusername = git log -1 --pretty=format:'%an'
$gitemail = git log -1 --pretty=format:'%ae'
$gitsubject = git log -1 --pretty=format:'%s'
$gitbody = git log -1 --pretty=format:'%b'


git config --global user.name "$gitusername" 
git config --global user.email "$gitemail" 

git checkout master
git reset --soft HEAD~1
git stash save "MasterChange"
git checkout ftc13474
git stash pop
git commit -a -m "$gitsubject"
git push


git checkout master
git reset --soft HEAD~1
git stash save "MasterChange2"
git checkout ftc16776
git stash pop
git commit -a -m "$gitsubject"
git push

