const searchClient = algoliasearch(
  'A3J8W241AO',
  '06ddc5a4aa410a6a4904d81608612d46' // search only API key, not admin API key
);

const search = instantsearch({
  indexName: 'Bordeaux',
  searchClient,
  routing: true,
});

search.addWidget(
  instantsearch.widgets.configure({
    hitsPerPage: 20,
  })
);

search.addWidget(
  instantsearch.widgets.searchBox({
    container: '#search-box',
    placeholder: 'Search for Bordeaux Wines',
    autofocus: false
  })
);

search.addWidget(
  instantsearch.widgets.hits({
    container: '#hits',
    templates: {
    	empty: `Our sommelier couldn't find anything for your request.`,
        item: function(hit) {
            return hitTemplate(hit);
          }
    },
  })
);

search.addWidget(
	    instantsearch.widgets.rangeSlider({
	      container: '#year',
	      attribute: "year",
	    })
);

search.addWidget(
	    instantsearch.widgets.rangeSlider({
	      container: '#price',
	      attribute: "price",
	    })
);

search.addWidget(
	instantsearch.widgets.sortBy({
		  container: '#sort-by',
		  items: [
		    { label: 'Relevant', value: 'Bordeaux' },
		    { label: 'Price (asc)', value: 'Bordeaux_price_asc' },
		    { label: 'Price (desc)', value: 'Bordeaux_price_desc' },
		    { label: 'Quality (asc)', value: 'Bordeaux_quality_asc' },
		    { label: 'Quality (desc)', value: 'Bordeaux_quality_desc' },
		  ],
	})
);

search.addWidget(
	    instantsearch.widgets.pagination({
	      container: '#pagination',
	      scrollTo: '#search-box',
	    })
);

search.addWidget(
	    instantsearch.widgets.menu({
	      container: '#type',
	      attribute: 'type',
	      sortBy: ['isRefined', 'count:desc', 'name:asc'],
	      templates: {
	        header: getHeader('Type'),
	      },
	    })
);

search.addWidget(
		  instantsearch.widgets.stats({
		    container: "#stats",
		    templates: {
		      body(hit) {
		        return `<strong>${hit.nbHits}</strong> results found ${
		          hit.query != "" ? `for <strong>"${hit.query}"</strong>` : ``
		        } in <strong>${hit.processingTimeMS}ms</strong>`;
		      }
		    }
		  })
);

function hitTemplate(hit) {
	  var result = `
	    <div class="hit">
      <div class="hit-image">
        <img src="${hit.image}" />
      </div>
      <div class="hit-content">
        <div>
          <div class="hit-name">${hit._highlightResult.name.value}</div>
          <div class="hit-year">${hit._highlightResult.year.value}</div>
          <div class="hit-type">${hit._highlightResult.type.value}</div>`;
          
      if(hit.quality < 95){
    	  result += `<div class="hit-quality">${hit.quality}</div>`;
      }
      else {
    	  result += `<div class="hit-quality-excellent">${hit.quality}<img id="excellent" src="resources/excellent_wine.png"></div>`
      }

	  result += `</div>
	        <div class="hit-price">$${hit.price}</div>
	      </div>
	    </div>`;
	    
	  return result;
}
function getHeader(title) {
	var header = document.createElement("header"),
    h4 = document.createElement("h4");
	
	h4.textContent = title;
	return h4;
}

search.start();