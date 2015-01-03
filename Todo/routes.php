<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/
/*
Event::listen('404', function()
{
	return Response::error('404');
});

Event::listen('500', function()
{
	return Response::error('500');
});
*/

//Test
Route::get('/', function()
{
	return 'Sexyyyyyy';
});

Route::get('show', 'HomeController@showWelcome');

//Get Tasks by userId
Route::get('/task/{userId}', 'TodosController@get_index');

//Add Task
Route::post('/task/add', 'TodosController@post_index');

//Delete Task by taskId
Route::delete('/task/{id}', 'TodosController@delete_index');

//Archive Task by taskId
Route::delete('/task/archive/{id}', 'TodosController@archive_index');

//Edit Task
Route::put('/task/update', 'TodosController@put_index');

//Get Comments
Route::get('/task/comment/{taskId}', 'TodosController@get_comment');

//Add Comment
Route::post('/task/comment/add', 'TodosController@post_comment');

//Get Archived Tasks (SoftDeleted Tasks only)
Route::get('/task/archive/{userId}', 'TodosController@get_archive');


Route::get('gauth/{auth?}', 'TodosController@get_login');



Route::filter('before', function($request)
{
	// Do stuff before every request to your application...
	 $request->headers->set('content-type','application/json');
});
