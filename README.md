Understanding setIntent
========================

It would appear that 'setIntent' does not alter the intent of a destroyed
activity.  This can be demonstrated by setting your phone 'Developer options'
to have 'Don't keep activities' checked and then executing this code.

The desired behaviour might be:

* Activity -> 'Search A'
* Activity -> 'Search B'
* Home
* Activity -> 'Search B' 

The undesired behaviour, as shown with #setIntent, is:

* Activity -> 'Search A'
* Activity -> 'Search B'
* Home
* Activity -> 'Search A'

```
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        /* we want future calls to getIntent have this new intent. e.g. new
           search not the one at creation */
        
        // desired behaviour
        // finish();
        // startActivity(intent);

        // undesired behaviour
        setIntent(intent);
        handleIntent(intent);
    }
```
