'use strict';

angular.module('voteApp.version', [
  'voteApp.version.interpolate-filter',
  'voteApp.version.version-directive'
])

.value('version', '0.1');
