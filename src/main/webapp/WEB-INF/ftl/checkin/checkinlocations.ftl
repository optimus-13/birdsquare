<!DOCTYPE html>
<html>
<head>
<#include "../includes.ftl" />
    <title>BirdSquare: Locations</title>
    <script
            src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
    </script>
    <script type="text/javascript" src="javascript/location.js"></script>
    <script type="text/javascript">getLocation(); </script>
</head>

<body>

<div data-role="page" id="checkinlocations"><!--this div is closed in footer-->
<#include "../headers/header-without-button.ftl" />

    <div data-role="content" id="checkinlocations-content">
        <p>Select a location </p>
        <ul id="location-container" data-filter-placeholder="Filter items" data-role='listview' data-inset='true' data-filter='true'>
        </ul>
        <ul data-role='listview' data-inset='true'>
            <li><a onclick="document.getElementById('form-addlocation').submit()" rel="external">Create new location</a></li>
        </ul>
    </div>

<#include "../footer.ftl" />
</div>

</body>
</html>