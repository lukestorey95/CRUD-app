@extends('layout')

@section('content')

<h1>New Note</h1>

<form method="POST" action="/notes">
  @csrf
  <div class="form-floating mb-3 needs-validation">
      <input type="text" class="form-control" name="note" placeholder="note" required>
      <label>What do you want to remember?</label>
    </div>

    <div>
      <input type="submit" value="Submit" class="btn btn-primary">
    </div>
  </div>
</form>



@endsection