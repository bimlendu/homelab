FROM ruby:2.3.4-alpine

LABEL maintainer "Bimlendu Mishra"

RUN echo "@community http://dl-cdn.alpinelinux.org/alpine/edge/community" >> /etc/apk/repositories

RUN apk --no-cache add \
    --virtual .build-dependencies \
    gcc g++ make cmake linux-headers go python \
    icu-dev ruby-dev musl-dev \
    yarn@community go@community=1.8.3-r0

RUN apk --no-cache add \
    --virtual .runtime-dependencies \
    openssh git nodejs icu-libs sudo procps

RUN apk info

#RUN apk del .build-dependencies
