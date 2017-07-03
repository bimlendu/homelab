#!/usr/bin/env bash
# shellcheck disable=SC2086

set -e

IMAGE=$( < Dockerfile grep FROM | cut -d' ' -f2 )
PRIVATE_REPO="registry-private.jarvis.local.net:5002"

BUILD_NUMBER=$(< BUILD_NUMBER)
BUILD_NUMBER=${BUILD_NUMBER// /}

echo "Incrementing build number."
BUILD_NUMBER=$((BUILD_NUMBER+1))
echo $BUILD_NUMBER > BUILD_NUMBER

IMAGE=${IMAGE}-b${BUILD_NUMBER}

docker build --squash -t ${IMAGE} -t ${PRIVATE_REPO}/homelab/${IMAGE} .

docker push ${PRIVATE_REPO}/homelab/${IMAGE}

curl -s 'https://registry-private.jarvis.local.net:5002/v2/homelab/jenkins/tags/list' | jq
