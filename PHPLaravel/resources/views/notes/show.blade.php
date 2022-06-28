@extends('layout')

@section('content')
<h1>{{$heading}}</h1>


<div class="card" style="width: 18rem">
  <div class="card-body">
    <p class="card-text">{{$note->note}}</p>

    <a class="btn btn-primary" href="/notes/{{$note->id}}/edit">Edit</a>

    <form method="POST" action="/notes/{{$note->id}}/">
      @csrf
      @method("DELETE")
      <input class="btn btn-primary" type="submit" value="Delete" />
    </form>
  </div>
</div>

@endsection