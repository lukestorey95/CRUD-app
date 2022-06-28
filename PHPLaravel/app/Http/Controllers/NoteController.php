<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Note;

class NoteController extends Controller
{
    public function index()
    {
        return view('notes.index', [
            'heading' => 'Notes',
            'notes' => Note::all(),
        ]);
    }

    public function show(Note $note)
    {
        return view('notes.show', [
            'heading' => 'Note',
            'note' => $note,
        ]);
    }

    public function create()
    {
        return view('notes.create');
    }

    public function store(Request $request)
    {
        $formFields = $request->validate([
            'note' => 'required',
        ]);

        Note::Create($formFields);

        return redirect('/');
    }

    public function edit(Note $note)
    {
        return view('notes.edit', [
            'note' => $note,
        ]);
    }

    public function update(Request $request, Note $note)
    {
        $formFields = $request->validate([
            'note' => 'required',
        ]);

        $note->update($formFields);

        return redirect('/');
    }

    public function destroy(Note $note)
    {
        $note->delete();

        return redirect('/');
    }
}
