# Movie DB

This project showcases how to use popular Android libraries like Retrofit & Picasso

## RetrofitAdapter

This class is a simple Singleton wrapper for Retrofit class. It can be used to initialize Retrofit and also to initialize Service interfaces. It serves as an interceptor for Http Client to add headers to the requests.

## ServiceFactory

This is a Factory for all the Service Interfaces in your app. It uses the RetrofitAdapter to initialize Retrofit. It is used by BaseActivity to make any service calls.
