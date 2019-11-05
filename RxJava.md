# Rx Java

## Agenda
- Functional Reactive concepts
- Observable creation, composition and filtering
- Observable transformation and conditional operations
- Connectable Observables, Resource management and publish/subscribe Subjects
- Implementation patters

## Introduction
- Functional Reactive concept
    - Pure Functions - Are those whose state remains same irrespective of no.of threads. i.e. no need of synchronizations  i.e. the result remains same for respective input parameters
    - Functions as first class citizen - sending functions as parameters. interms as variables/parameters
    - High order functions- Takes functions and return a compasited function; - they form the building bloc of reactive - composition and lazy execution
- Reactive Programming
    - 4 primary concepts of reactive programming
        - Event Driven - code is organized in a way to facilitate concurrency , more maintainable
        - Scalable
        - Resilient 
        - Responsive 
    - Event Driven Property 
        - Observer Pattern is used in Rx Java for Event Driven
            - You have Observable and Observers
            - Observers are passive they sit idle without using any resource till there is an event 
            - Observable Notifies the observers concurrently in the case of an event.
## Observables creation, composition and filtering
- Observable creation - lifecycle
    - Observer event -
        - onNext
        - onCompleted
        - onError
    - Subscription events
        - unsubscribe
- Types of Observable
    - Observable - no blocking observable - events are asynchronous
    - BlockingObservable - Events are synchronous
- Schedulers
    - onComputational
    - io
    - newThead

## Observables Transformation and Conditional Operations
### Transformation
- map operation /transformation- one input to one output
- flat map transformation - one input to many output
- scan operation- output is part of next transformation to next event 
- groupBy transformation -
- buffer  - buffers the event for particular specified timespan and emits
### conditional operation
- defaultIfEmpty
- IfNotEmpty
- skipWhile
- takeWhile
- takeWhileWithIndex
- ambiquous operator - takes two observable and emits event which ever occurs first
- skipUntil
- takeUntil

## Connectable Observables and Resource management
- Connectable Observable
- 