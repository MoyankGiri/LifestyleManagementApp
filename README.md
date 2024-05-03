# LIFESTYLE MANAGEMENT APP

## Problem Statement
Design an android application in order to track and provide insights about a user’s lifestyle with respect to health using graphs and utility functions

## Introduction
This project proposes an application which is designed for helping a user in tracking their daily lifestyle and provide them with certain insights on the same.
### What?
An android application that provides users insights about their lifestyle with
respect to their inputs.
### Why?
In order to provide user a way to know how healthy their lifestyle is and how
they might improve it.
### How?
The main procedure includes taking inputs from the user about the food they
have taken and the exercise they have done in the day. Based on the user’s
input the calories intake and burnt is calculated by using values stores in an
SQLite table and this value is then added to the total calorie intake/calorie
burnt value of a particular day.
### Who?
This application is designed to be used by any user of any age with the sole
purpose to provide insights about the user’s lifestyle.

## Solution Architecture
* The user is allowed to sign up and create an account by using their email addresses or sign in using a pre-existing account
* The application makes use firebase in order to provide the user with authentication.
* All accounts created are stored in firebase for authentication purposes.
* The user is given three tries to login into the account. After three unsuccessful attempts, the sign in button is disabled
* The following describes the various technologies used in the application:
    1. Building the Application: Java programming language
    2. Database: SQLite Database
    3. Authentication and user account storage: Firebase
* The application described here consists of three main features namely:
  1. Calorie Intake Calculation
  2. Calorie Burnt Calculation
  3. BMI calculator
