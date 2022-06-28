@extends('layout')

@section('content')

<h1>Edit Note</h1>

<form method="POST" action="/notes/{{$note->id}}">
  @csrf
  @method("PUT")
  <div class="form-floating mb-3 needs-validation">
      <input type="text" class="form-control" name="note" placeholder="note" value="{{$note->note}}" required>
      <label>What do you want to change?</label>
    </div>

    <div>
      <input type="submit" value="Submit" class="btn btn-primary">
    </div>
  </div>
</form>



@endsection