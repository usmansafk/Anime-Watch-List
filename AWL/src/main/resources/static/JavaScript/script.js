const API_URL = "http://localhost:9092";
let createNewFlag = false;
let updateFlag = false;
let selectedId = "";

function onClickCreateNew() {
  if (!updateFlag) createNewFlag = true;
}

class AnimeWatchList {
  constructor(name, count, rating) {
    this.name = name;
    this.count = count;
    this.rating = rating;

    if (createNewFlag) {
      createNewFlag = false; //setting creatNewRequest Flag to false
      fetch(`${API_URL}/create`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: this.name,
          episode: this.count,
          rating: this.rating,
        }),
      }).then((res) => {
        this.name = ""; //clearing up variables
        this.count = "";
        this.rating = "";
        location.reload(true);
        return res.json();
      });
    }

    if (updateFlag) {
      updateFlag = false;
      fetch(`${API_URL}/update/` + selectedId, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: selectedId,
          name: this.name,
          episode: this.count,
          rating: this.rating,
        }),
      }).then((res) => {
        this.name = ""; //clearing up variables
        this.count = "";
        this.rating = "";
        selectedId = "";
        location.reload(true);
        return res.json();
      });
    }
  }
}

class CRUD {
  updateWatchList = false;
  updateTarget = null;
  animes = "";

  //READ
  static async showAnimes() {
    const animes = await Anime.getAnime();
    animes.forEach((anime) => {
      CRUD.addAnimeToWatchList(anime);
    });
  }

  //CREATE
  static addAnimeToWatchList(anime) {
    const table = document.querySelector("#watchlist");
    const row = document.createElement("tr");

    row.innerHTML = `
        <td>${anime.name}</td>
        <td>${anime.episode}</td>
        <td>${anime.rating}</td>
        <td><i class="fas fa-trash-alt text-danger" onClick="CRUD.deleteAnime(event, '${anime.name}')"></i></td>
        <td><i class="far fa-edit text-primary" onClick="CRUD.updateAnime(event, '${anime.name}')"></i></td>`;

    console.log("ADD ANIME CALL MADE");
    table.appendChild(row);
  }

  //UPDATE
  static updateAnimeInWatchList(anime) {
    if (CRUD.updateTarget && CRUD.updateWatchList) {
      let row = CRUD.updateTarget.parentElement.parentElement;
      row.innerHTML = `
          <td>${anime.name}</td>
          <td>${anime.count}</td>
          <td>${anime.rating}</td>
          <td><i class="fas fa-trash-alt text-danger" onClick="CRUD.deleteAnime(event, '${anime.name}')"></i></td>
          <td><i class="far fa-edit text-primary" onClick="CRUD.updateAnime(event, '${anime.name}')"></i></td>`;
    }
  }

  // () for UPDATING and DELETING
  static async updateAnime(event, name) {
    CRUD.updateTarget = event.target;
    CRUD.updateWatchList = true;
    updateFlag = true;

    let animeList = await Anime.getAnime();
    animeList.forEach((anime, id) => {
      if (anime.name === name) {
        selectedId = anime.id;
        document.querySelector("#name").value = anime.name;
        document.querySelector("#watched").value = anime.episode;
        document.querySelector("#rating").value = anime.rating;
      }
    });

    document.getElementById("create-button").innerHTML = "UPDATE ANIME";
  }

  static deleteAnime(event, name) {
    event.target.parentElement.parentElement.remove();
    Anime.deleteAnime(name);
    CRUD.confirmCRUD("Anime removed from watch list", "danger");
  }

  static refreshInputBox() {
    document.querySelector("#name").value = "";
    document.querySelector("#watched").value = "";
    document.querySelector("#rating").value = "";
    CRUD.updateWatchList = false;
    CRUD.updateTarget = null;
    document.querySelector("#name").disabled = false;
    document.getElementById("create-button").innerHTML = "ADD TO MY WATCHLIST";
  }

  static confirmCRUD(message, classname) {
    const div = document.createElement("div");
    div.className = `alert alert-${classname}`;
    div.innerHTML = message;
    const container = document.querySelector(".container");
    container.insertBefore(div, document.querySelector("#anime-form"));

    setTimeout(() => {
      document.querySelector(".alert").remove();
    }, 4000);
  }
}
//------------------------------------------------------------------

class Anime {
  static async getAnime() {
    let animes;
    await fetch(`${API_URL}/getAll`)
      .then(function (response) {
        return response.json();
      })
      .then(function (data) {
        if (data != undefined) {
          animes = data;
          console.log(data);
          return animes;
        }
      });
    return animes;
  }

  static async deleteAnime(name) {
    const animes = await Anime.getAnime();
    animes.forEach((anime, id) => {
      if (anime.name === name) {
        fetch(`${API_URL}/remove/` + anime.id, {
          method: "DELETE",
        }).then((res) => {
          console.log("deleted");
        });
       
      }
    });

    localStorage.setItem("animes", JSON.stringify(animes));
  }

  static addAnime(anime) {
    const animes = Anime.getAnime();
    if (CRUD.updateWatchList) {
      Anime.updatedAnime(anime);
    } else {
      
      fetch(`${API_URL}/create`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: anime.name,
          episode: anime.watched,
          rating: anime.rating,
        }),
      }).then((res) => {
        
        return res.json();
      });
     
    }
  }
}
//------------------------------------------------------------------
document.addEventListener("DOMContentLoaded", CRUD.showAnimes());

document.querySelector("#anime-form").addEventListener("submit", (e) => {
  e.preventDefault();
  const name = document.querySelector("#name").value;
  const count = document.querySelector("#watched").value;
  const rating = document.querySelector("#rating").value;

  if (name === "") {
    CRUD.confirmCRUD("Please enter an anime name ", "danger");
  } else if (count === "") {
    CRUD.confirmCRUD("Please enter which episode you are on", "danger");
  } else if (rating === "") {
    CRUD.confirmCRUD("Please enter your rating for the show", "danger");
  } else {
    const anime = new AnimeWatchList(name, count, rating);
    if (createNewFlag) {
      Anime.addAnime(anime);
    }

    if (CRUD.updateWatchList) {
      CRUD.updateAnimeInWatchList(anime);
      message = "Anime updated";

      CRUD.confirmCRUD("Anime updated", "success");
      CRUD.refreshInputBox();
    } else {
      
      CRUD.addAnimeToWatchList(anime);
      
      CRUD.confirmCRUD("Anime added", "success");
      CRUD.refreshInputBox();
    }
  }
});
