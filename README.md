![Logo][https://github.com/marcellogalhardo/quandoo/blob/master/design/icon.png]

# Quandoo android challenge

You can download the APK [here]() or see the images [here](/design).

## Task

We need an application to make reservations in a restaurant.

## Workflow

The waiter opens the app, chooses a customer from a list, the app navigates him to a table choosing screen. The waiter chooses the table and the app highlights the chosen table. ​ Every 10 minutes all reservations have to be removed even if the application is closed.

## API description

Each customer has First Name, Last Name and Id.

The tables are represented as one-dimensional array of booleans where “true” means that the table is available and “false” that it is not.

* URL to customers list: ​ https://s3-eu-west-1.amazonaws.com/quandoo-assessment/customer-list.json
* URL to tables list: ​ https://s3-eu-west-1.amazonaws.com/quandoo-assessment/table-map.json

## Requirement

1. Tables have to be represented as cells on grid
2. Available and unavailable tables have to be easily recognized
3. The app has to also work offline
4. Search option for customers would be a plus
5. Any unspecified details are left to your imagination

## What we will review

1. Unit and instrumentation test coverage
2. Architecture
3. Code style
4. Code extensibility
5. Performance

## Implementation details

1. It use the concept of Package by Feature / Domain.
2. It use MVP pattern for presentation and to make tests easier.
3. It use Dagger for Dependency Injection.
4. It use Repository Pattern with OkHttp, Retrofit and Json for data loading.
5. It use RxJava with Retrolambda.
6. It use ButterKnife for view bind.
7. It use SVG.
8. It has some unit testing with JUnit and Mockito (see test folder).
9. It has some Ui tests with Espresso (see androidTest folder).
10. It use Hawk to cache (work offline).

**Important:** Unit and Ui tests **must run with Mock flavor**.

## TODO: Improvements if I've more time

1. Export String resources from Ui.
2. Better testing coverage.
3. Refactoring.
4. Style improvement.
5. Better git flow / commits.

## UX / UI

![Launch][https://github.com/marcellogalhardo/quandoo/blob/master/design/1_launch.png]
![CustomerList][https://github.com/marcellogalhardo/quandoo/blob/master/design/2_customer_list.png]
![CustomerListFiltered][https://github.com/marcellogalhardo/quandoo/blob/master/design/3_customer_list_filtered.png]
![TableMap][https://github.com/marcellogalhardo/quandoo/blob/master/design/4_table_map.png]
![DialogPositive][https://github.com/marcellogalhardo/quandoo/blob/master/design/5_dialog_positive.png]
![DialogNegative][https://github.com/marcellogalhardo/quandoo/blob/master/design/6_dialog_negative.png]

