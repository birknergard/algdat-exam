# Exam in Data Structures and Algorithms
(by Birk Nergaard, and Cathrine Steiner)

# Useful git commands

## For creating commits
    git add -A
    git commit -m "your commit message"

## For pushing changes to repository
    git push origin <branch_name>

## For pulling new changes from repository
    git pull origin <branch_name>
    --> for rebase add --rebase
    
## For switching branch
    git checkout <branch_name>

## For rebasing branch
    git checkout main
    git pull origin main
    git checkout <your_branch>
    git rebase origin/main
    
    NB!! VERIFY DATA IS AS EXPECTED BEFORE NEXT STEP!
    git push origin <branch_name> -f


