<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateNewsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('news', function (Blueprint $table) {
            $table->id();
            $table->string(column:'title');
            $table->string(column:'author');
            $table->string(column:'source');
            $table->string(column:'url')->nullable();
            $table->string(column:'url_image')->nullable();
            $table->string(column:'description');
            $table->string(column:'content');
            $table->timestamp(column:'published_at');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('news');
    }
}
