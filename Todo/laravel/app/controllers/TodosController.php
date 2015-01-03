<?php

class TodosController extends BaseController {

	public $restful = true;

	public function get_index($userId) 
	{

			$tasks =Task::where('email', '=', $userId)->get();
			return $tasks;
	}

	public function post_index() 
	{

		$task = new Task();
		$task->title = Input::get('title');
		$task->description = Input::get('description');
		$task->status = Input::get('status');
		$task->priority = Input::get('priority');
		$task->email = Input::get('email');
		$task->save();
		return $task;
	}

	public function put_index() 
	{
		
		$task = Task::find(Input::get('id'));

		$task->title = Input::get('title');
		$task->description = Input::get('description');
		$task->status = Input::get('status');
		$task->priority = Input::get('priority');
		$task->email = Input::get('email');
		$task->save();
		return $task;
    }
	
    public function delete_index($id = null) 
    {
    	//Implement **force delete

		$task = Task::find($id);

		$deletedtask = $task;

		$task->forceDelete(); 
		return $deletedtask;   
    }


    public function archive_index($id = null) 
    {
		$task = Task::find($id);

		$deletedtask = $task;
		$task->delete();     
		return $deletedtask;   
    }


    public function get_comment($taskId) 
	{
			$comments =Comment::where('task_id', '=', $taskId)->get();
			return $comments;
	}

	public function post_comment() 
	{
		$cmt = new Comment();
		$cmt->comment = Input::get('comment');
		$cmt->task_id = Input::get('task_id');
		$cmt->save();
		return $cmt;
	}

	public function get_archive($userId) 
	{
		
			$tasks = Task::onlyTrashed()->where('email', '=' , $userId)->get();
			return $tasks;
	}


    public function get_login($auth = null) 
    {

    	if ($auth == 'auth')
        {
		  try {
		         Hybrid_Endpoint::process();
		      }
		      catch ( Exception $e ) {
		         echo "Error at Hybrid_Endpoint process: $e";
		      }

        }


    		$config = array(
			"base_url" => "http://todo.com/gauth/auth",
			"providers" => array (
									"Google" => array (
														"enabled" => true,
														"keys" => array ( "id" => "944714286318-kqn30nupbs08bte1fj5d089rmm2oq72l.apps.googleusercontent.com", "secret" => "90D0zLVbVIxtkTUkO4G7hD1B" ),
														"scope" => "https://www.googleapis.com/auth/userinfo.profile ". // optional
														"https://www.googleapis.com/auth/userinfo.email" // optional
			)));


			try
			{
	            $oauth = new Hybrid_Auth($config);
	            $provider = $oauth->authenticate('Google');
	            $profile = $provider->getUserProfile();
	        }
	        catch(exception $e)
	        {
	            return $e->getMessage();
	        }
	    	return var_dump($profile);
	    	// return $profile->email;
		
	}


}

?>