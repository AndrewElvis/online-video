#!/bin/sh

git filter-branch --env-filter '
an="$GIT_AUTHOR_NAME"
am="$GIT_AUTHOR_EMAIL"
cn="$GIT_COMMITTER_NAME"
cm="$GIT_COMMITTER_EMAIL"
if [ "$GIT_COMMITTER_EMAIL" = "andrew@zust.com" ]
then
    cn="AndrewElvis"
    cm="838025597@qq.com"
fi
if [ "$GIT_AUTHOR_EMAIL" = "andrew@zust.com" ]
then
    an="AndrewElvis"
    am="838025597@qq.com"
fi
    export GIT_AUTHOR_NAME="$an"
    export GIT_AUTHOR_EMAIL="$am"
    export GIT_COMMITTER_NAME="$cn"
    export GIT_COMMITTER_EMAIL="$cm"
'