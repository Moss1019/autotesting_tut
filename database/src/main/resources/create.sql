create database trader_db;

create table account (account_id integer primary key generated always as identity,
username char(32),
account_number char(36));

create table currency (currency_id integer primary key generated always as identity,
title char(32));

create table candle( candle_id integer primary key generated always as identity,
open_price double precision,
close_price double precision,
high_price double precision,
low_price double precision,
currency_id integer references currency(currency_id)
);

create table trade (trade_id integer primary key generated always as identity,
bid_price double precision,
open_time timestamp,
currency_id integer references currency(currency_id),
account_id integer references account(account_id));
