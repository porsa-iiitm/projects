package com.prankul.model;

import java.io.Serializable;
import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

import com.prankul.todo.Utillities;

public class Task implements Comparable,Parcelable
{
	private Integer id;
	private String title;
	private String description;
	private String status;
	private String priority;
	private String created_at;
	private String updated_at;
	private String email;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int compareTo(Object o) 
	{
		
		// TODO Auto-generated method stub
		Task other = (Task)o;  
		
		 // Sort Based on Priority
		if ( Utillities.PriorityToValue(priority) > Utillities.PriorityToValue(other.getPriority())) {  
		return -1;  
		} else if ( Utillities.PriorityToValue(priority) < Utillities.PriorityToValue(other.getPriority()) ) {  
		return 1;  
		}  
		 
		// If Priorities are same then sort on the basis of time
		if ( Utillities.DateToTimestamp(created_at) < Utillities.DateToTimestamp(other.getCreated_at()) ) {  
		return -1;  
		} else if ( Utillities.DateToTimestamp(created_at) > Utillities.DateToTimestamp(other.getCreated_at())  ) {  
		return 1;  
		}  
		
		return 0;  
	}
	  public static final Parcelable.Creator<Task> CREATOR = new Creator<Task>() {  
		  public Task createFromParcel(Parcel source) {  
			  Task task = new Task();  
		      task.id = source.readInt();  
		      task.title = source.readString();  
		      task.description = source.readString(); 
		      task.status = source.readString();  
		      task.priority = source.readString();  
		      task.created_at = source.readString();  
		      task.updated_at = source.readString();  
		      return task;  
		  }  
		  public Task[] newArray(int size) {  
		      return new Task[size];  
		  }  
		     };  

		     public int describeContents() {  
		  return 0;  
		     }  
		     public void writeToParcel(Parcel parcel, int flags) {  
		  parcel.writeInt(id);  
		  parcel.writeString(title);  
		  parcel.writeString(description); 
		  parcel.writeString(status);  
		  parcel.writeString(priority);  
		  parcel.writeString(created_at); 
		  parcel.writeString(updated_at);  
		  parcel.writeString(email);  
		  
		     }  
	
}
