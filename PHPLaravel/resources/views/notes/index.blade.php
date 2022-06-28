@extends('layout')

@section('content')

<h1>{{$heading}}</h1>


@if(count($notes) == 0)
<p>No Notes</p>
@endif

<div id="notes" class="row">
  @foreach($notes as $note)
    <div class="col-sm-6">
      <div id="<%= dom_id note %>" class="card" style="width: 18rem">
        <div class="card-body">
          <p class="card-text">
            <a href="/notes/{{$note->id}}">{{$note->note}}</a>
          </p>
        </div>
      </div>
    </div>
  @endforeach 
</div>

@endsection